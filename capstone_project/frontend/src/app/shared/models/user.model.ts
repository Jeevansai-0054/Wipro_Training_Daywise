export interface User {
  id: number;
  username: string;
  email: string;
  fullName: string;
  address?: string;
  phone?: string;
  roles: string[];
  enabled: boolean;
  createdAt: string;
  updatedAt: string;
}

export interface UserCreateDto {
  username: string;
  email: string;
  password: string;
  fullName: string;
  address?: string;
  phone?: string;
}

export interface UserUpdateDto {
  id: number;
  username?: string;
  email?: string;
  fullName?: string;
  address?: string;
  phone?: string;
}

export interface AuthRequest {
  username: string;
  password: string;
}

export interface AuthResponse {
  accessToken: string;
  refreshToken: string;
  tokenType: string;
  expiresIn: number;
  user: User;
}

export interface MenuItem {
  label: string;
  route: string;
  rolesAllowed: string[];
}
