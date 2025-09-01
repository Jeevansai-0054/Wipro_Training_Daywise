import { TestBed } from '@angular/core/testing';
import { App } from './app';

describe('App', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [App],
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(App);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it('should correctly identify palindromes', () => {
    const fixture = TestBed.createComponent(App);
    const app = fixture.componentInstance;

    expect(app.isPalindrome('madam')).toBeTrue();
    expect(app.isPalindrome('Racecar')).toBeTrue();
    expect(app.isPalindrome('A man, a plan, a canal: Panama')).toBeTrue();
    expect(app.isPalindrome('hello')).toBeFalse();
    expect(app.isPalindrome('Angular')).toBeFalse();
  });
});
