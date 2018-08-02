import { text } from '@angular/core/src/render3/instructions';
import { DialogService } from './../../dialog.service';
//import { ResponseApi } from './../../model/response-api';
import { MobileService } from './../../services/mobile.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ResponseApi } from 'src/app/model/response-api';
@Component({
  selector: 'app-mobile-list',
  templateUrl: './mobile-list.component.html',
  styleUrls: ['./mobile-list.component.css']
})
export class MobileListComponent implements OnInit {

  page:number=0;
  count:number=5;
  pages:Array<number>;
  message: {};
  classCss:{};
  listMobile = [];

  constructor(private dialogService: DialogService,
              private mobileService: MobileService,
              private router: Router) {

               }

  ngOnInit() {
    this.findAll(this.page, this.count);
  }
//ResponseApi
  findAll(page:number, count:number){
    this.mobileService.findAll(page,count).subscribe((responseApi : ResponseApi) =>{
      this.listMobile = responseApi['data']['content'];
      console.log(this.listMobile);
      this.pages = new Array(responseApi['data']['totalPages']);
    }, err =>{
      this.showMessage({ 
        type: 'error', 
        text: err['error']['errors'][0]
      });
    });
  }

  delete(id:string){
    this.dialogService.confirm('Do you whant to delete the mobile?').then((candelete:boolean) =>{
      if(candelete){
        this.message = {};
        this.mobileService.delete(id).subscribe((responseApi: ResponseApi) => {
          this.showMessage({
            type: 'success',
            text: 'Record deleted'
          });
          this.findAll(this.page,this.count);
        }, err => {
          this.showMessage({
            type: 'error',
            text: err['error']['errors'][0]
          });
        });
      }
    });
  }

  setNextpage(event:any) {
    event.preventDefault();
    if(this.page+1 < this.pages.length) {
      this.page = this.page + 1;
      this.findAll(this.page, this.count);
    } 
  }

  setPreviouspage(event:any) {
    event.preventDefault();
    if(this.page > 0) {
      this.page = this.page - 1;
      this.findAll(this.page, this.count);
    } 
  }

  setPage(i, event:any) {
    event.preventDefault();
    this.page = i;
    this.findAll(this.page, this.count);
  }

  edit(id:string){
    this.router.navigate(['/mobile-new', id]);
  }

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
}
