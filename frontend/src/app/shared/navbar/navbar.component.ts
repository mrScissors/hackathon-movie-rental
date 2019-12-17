import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  goHome() {
    this.router.navigate(['home']);
  }

  // goInventory() {
  //   this.router.navigate(['inventory']);
  // }

  // goVisual() {
  //   this.router.navigate(['visual']);
  // }

  goLogin() {
    this.router.navigate(['login']);
  }


}
