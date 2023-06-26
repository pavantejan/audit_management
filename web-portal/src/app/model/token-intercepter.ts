
import { HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { SecurityToken } from "./security-token";


@Injectable({
    providedIn:'root'
})
export class TokenIntercepter implements HttpInterceptor{
    token : any;

    constructor(private securityToken:SecurityToken){}

    intercept(req: HttpRequest<any>, next: HttpHandler){

        console.log("In intercepter");

        console.log(req);

        // String temp = ( this.securityToken.Jwt | localStorage.getItem("token") );
        // temp : string = "";

        // if( this.securityToken.Jwt == "" ){
        //     if( localStorage.getItem("token") != null )
        //         this.temp = localStorage.getItem("token");
        // }
        // else{
        //     this.temp = this.securityToken.Jwt;
        // }

        // this.token = this.securityToken.Jwt || sessionStorage.getItem('token');
        
        let reqHeader = req.clone({
            setHeaders : {
                Authorization : "Bearer " + this.securityToken.Jwt ,      //this.temp,
            }
        });
        console.log(reqHeader);

        return next.handle(reqHeader);
    }
}
