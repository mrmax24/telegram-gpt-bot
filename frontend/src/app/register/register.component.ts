import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import {Router} from "@angular/router";
import {environment} from "../../../environment";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  userRegisterDto = {
    login: '',
    email: '',
    password: '',
    repeatPassword: ''
  };

  errorMessage: string | undefined

  constructor(private http: HttpClient, private router: Router,
              private authService: AuthService) {
  }

  ngOnInit(): void {
  }

  goToLoginPage() {
    this.router.navigate(['auth/login']);
  }

  goToRegisterPage() {
    this.router.navigate(['auth/register']);
  }

  registerUser() {

    this.errorMessage = undefined
    const serverUrl = environment.serverUrl

    this.http.post<any>(`${serverUrl}/auth/register`, this.userRegisterDto).subscribe(
      response => {
        console.log(response);
        if (response) {
          this.authService.setAuthenticated(true);
          this.router.navigate(['/']);
        } else {
          this.errorMessage = 'Authentication failed';
        }
      },
      error => {
        if (error.status === 401) {
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
