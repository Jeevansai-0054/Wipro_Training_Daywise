import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { environment } from '../../../environments/environment';
import { User, AuthRequest, AuthResponse, UserCreateDto, UserUpdateDto, MenuItem } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private currentUserSubject = new BehaviorSubject<User | null>(null);
  public currentUser$ = this.currentUserSubject.asObservable();

  private tokenKey = 'access_token';
  private refreshTokenKey = 'refresh_token';

  constructor(private http: HttpClient) {
    this.loadUserFromStorage();
  }

  login(request: AuthRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${environment.apiBaseUrl}/users/login`, request)
      .pipe(
        tap(response => {
          this.setTokens(response.accessToken, response.refreshToken);
          this.currentUserSubject.next(response.user);
        })
      );
  }

  register(userData: UserCreateDto): Observable<User> {
    return this.http.post<User>(`${environment.apiBaseUrl}/users`, userData);
  }

  logout(): void {
    this.clearTokens();
    this.currentUserSubject.next(null);
  }

  getCurrentUser(): User | null {
    return this.currentUserSubject.value;
  }

  isAuthenticated(): boolean {
    return !!this.getToken();
  }

  hasRole(role: string): boolean {
    const user = this.getCurrentUser();
    return user ? user.roles.includes(role) : false;
  }

  isAdmin(): boolean {
    return this.hasRole('ADMIN');
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  getRefreshToken(): string | null {
    return localStorage.getItem(this.refreshTokenKey);
  }

  getUserMenu(userId: number): Observable<MenuItem[]> {
    return this.http.get<MenuItem[]>(`${environment.apiBaseUrl}/users/menu/${userId}`);
  }

  updateProfile(userData: UserUpdateDto): Observable<User> {
    return this.http.put<User>(`${environment.apiBaseUrl}/users`, userData)
      .pipe(
        tap(user => {
          this.currentUserSubject.next(user);
        })
      );
  }

  private setTokens(accessToken: string, refreshToken: string): void {
    localStorage.setItem(this.tokenKey, accessToken);
    localStorage.setItem(this.refreshTokenKey, refreshToken);
  }

  private clearTokens(): void {
    localStorage.removeItem(this.tokenKey);
    localStorage.removeItem(this.refreshTokenKey);
  }

  private loadUserFromStorage(): void {
    const token = this.getToken();
    if (token) {
      try {
        // Decode JWT to get user info
        const payload = JSON.parse(atob(token.split('.')[1]));
        const user: User = {
          id: payload.sub,
          username: payload.username,
          email: payload.email,
          fullName: payload.fullName,
          roles: payload.roles || [],
          enabled: true,
          createdAt: '',
          updatedAt: ''
        };
        this.currentUserSubject.next(user);
      } catch (error) {
        this.clearTokens();
      }
    }
  }
}
