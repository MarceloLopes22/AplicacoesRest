import { CLARO_MOBILE_API } from './mobile.api';
import { Mobile } from './../model/mobile.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Injectable()
export class MobileService {

  constructor(private http: HttpClient) {}

  createOrUpdate(mobile: Mobile) {    
      return this.http.post(`${CLARO_MOBILE_API}/api/mobile`, mobile);   
  }

  findAll(page:number,count:number){
    return this.http.get(`${CLARO_MOBILE_API}/api/mobile/${page}/${count}`);
  }

  findById(id:string){
    return this.http.get(`${CLARO_MOBILE_API}/api/mobile/${id}`);
  }
  
  delete(id:string){
    return this.http.delete(`${CLARO_MOBILE_API}/api/mobile/${id}`);
  }
}
