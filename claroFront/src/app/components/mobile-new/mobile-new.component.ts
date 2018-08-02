import { ResponseApi } from './../../model/response-api';
import { MobileService } from './../../services/mobile.service';
import { Component, OnInit } from '@angular/core';
import { ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Mobile } from '../../model/mobile.model';
import { ActivatedRoute } from '@angular/router';
import { text } from '@angular/core/src/render3/instructions';

@Component({
  selector: 'app-mobile-new',
  templateUrl: './mobile-new.component.html',
  styleUrls: ['./mobile-new.component.css']
})
export class MobileNewComponent implements OnInit {

  @ViewChild("form")
  form: NgForm
  
  mobile = new Mobile('', 0,'','','','','');
  message : {};
  classCss : {};

  constructor(private mobileService: MobileService,
              private route: ActivatedRoute) {}

  ngOnInit() {
    let id : string = this.route.snapshot.params['id'];
    if(id != undefined && id != null && id != ""){
      this.findById(id);
    }
  }

  findById(id:string) {
    this.mobileService.findById(id).subscribe((responseApi: ResponseApi) =>{
      this.mobile = responseApi.data;
    }, err =>{
      this.showMessage({
        type: 'error',
        text: err['error']['errors'][0]
      });
    });
  }

  register(){
    this.message = {};
    this.mobileService.createOrUpdate(this.mobile).subscribe((responseApi: ResponseApi) =>{
      this.mobile = new Mobile('',0,'','','','','');
      let mobileRet: Mobile = responseApi.data;
      this.form.resetForm();
      this.showMessage({
        type: 'success',
        text: `Registered ${mobileRet.model} successfully`
      });
    }, err => {
        this.showMessage({
          type: 'error',
          text: err['error']['errors'][0]
        });
      });
    };
  

  private showMessage(message: {type: string, text: string}) : void  {
    this.message = message;
    this.buildClasses(message.type);
    setTimeout(() => {
      this.message = undefined;
    }, 3000);
  }

  private buildClasses(type: string):void{
    this.classCss = {
      'alert': true
    }
    this.classCss['alert-'+type] = true;
  }

  getFromGroupClass(isInvalid: boolean, isDirty): {} {
    return {
      'form-group': true,
      'has-error': isInvalid && isDirty,
      'has-success': !isInvalid && isDirty
    };
  }
}
