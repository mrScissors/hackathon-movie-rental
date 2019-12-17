import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import {Router} from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router, private fb: FormBuilder, private userServ: UserService) { }

  loginForm = this.fb.group({
    username: [''],
    // password: ['']
  });

  ngOnInit() {
  }

  onSubmit() {
    console.log(this.loginForm.value);
    this.userServ.saveCurrentUser(this.loginForm.value);
  }

}
