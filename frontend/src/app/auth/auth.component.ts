import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent {

  adminLoginRequestDto = {
    email: '',
    password: ''
  };

  errorMessage: string | undefined;

  constructor(private authService: AuthService,
              private router: Router) {
  }

  goToLoginPage() {
    this.router.navigate(['auth/login']);
  }

  goToRegisterPage() {
    this.router.navigate(['auth/register']);
  }

  login() {
    this.authService.login(this.adminLoginRequestDto).subscribe(
      response => {
        console.log('Authentication response:', response);
        if (response.token) {
          this.authService.setToken(response.token);
          this.authService.setAuthenticated(true);

          const redirectUrl = this.authService.getAndClearRedirectUrl();
          this.router.navigate([redirectUrl || '/']);
        } else {
          this.errorMessage = 'Authentication failed';
        }
      },
      error => {
        if (error.status === 401) {
          const errorBody = error.error;
          this.errorMessage = errorBody.message || 'Authentication failed';
        } else if (error.status === 404) {
          const errorBody = error.error;
          this.errorMessage = errorBody.message || 'Authentication failed';
        } else if (error.status === 400 && error.error && error.error.errors) {
          this.errorMessage = error.error.errors.join(', ');
        } else {
          this.errorMessage = 'Authentication error';
        }
        console.error('Authentication error:', error);
      }
    );
  }
}
