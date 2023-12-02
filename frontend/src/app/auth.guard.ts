import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) {}

  canActivate(): boolean {
    if (this.authService.isLoggedIn() && !this.authService.isTokenExpired()) {
      return true;
    } else {
      this.authService.clearToken();
      this.router.navigate(['auth/login']);
      return false;
    }
  }
}
