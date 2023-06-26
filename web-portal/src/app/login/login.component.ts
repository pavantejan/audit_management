import { Component, OnInit } from '@angular/core';
import { AuthService } from '../service/auth-service';
import { SecurityToken } from '../model/security-token';
import { LoginStatus } from '../model/login-status';
import { AuthResponse } from '../model/auth-response';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = "";
  password = "";
  message = "";
  // isDisabled:boolean = true;

  // constructor(private loginStatus:LoginStatus){

  // }
  // private firstObservable:Subscription;

  constructor(
    private authService: AuthService,
    private securityToken:SecurityToken,
    private loginStatus:LoginStatus,
    private authResponse:AuthResponse,
    private router:Router
  ) { }


  ngOnInit(): void {

    // this.isDisabled = this.username==='' && this.password==='';
    // this.securityToken.Jwt="";
    // console.log
    // console.log("xyz " + this.username + " hi");
  }
  logIn() {
    

    if (this.username.trim().length == 0 || this.password.trim().length == 0) {
      this.message = "Please provide valid credentials";
    }

    this.authService.generateSecurityToken(this.username, this.password).subscribe(
      (data) => {
        // console.log("inside generate data point");
        // console.log(data);
        if (data.includes(".")) {
          this.message = "";
          this.loginStatus.Status=true;
          this.securityToken.Jwt=data;
          // console.log("Inside true data");
          sessionStorage.setItem("token",this.securityToken.Jwt);
        }else if(data.includes("Invalid")){
          this.message = "Please provide valid credentials";
          // console.log("Inside wrong cred");
        }
      },
      (err) => {
        // this.message = "Please provide valid credentials";
        console.log(err);
        // console.log("Inside error");
        this.router.navigate(['error']);
      },
      ()=>{
        console.log(this.message);
        if(this.loginStatus.Status){
          // console.log("Validate");
          this.authService.validateToken(this.securityToken.Jwt).subscribe(
            (data:any) =>{
              // console.log("Validate1");
              // console.log(data.name);

              this.authResponse.Name =  data.name;
              this.authResponse.ProjectName = data.projectName;
              this.authResponse.IsValid = data.isValid;                         
            },
            (err) =>{
              this.router.navigate(['error']);
            },
            () =>{
              console.log("inside login login login");
              // localStorage.setItem("token",this.securityToken.Jwt);
              sessionStorage.setItem("token",this.securityToken.Jwt);

              console.log("security token : "+ this.securityToken.Jwt);
              // console.log("redirect to checklist");
              this.router.navigate(['checklist']);
            }
          )
        }
      }
    );
  }

}