import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class CarApiService {
  readonly url:string = "http://localhost:8080/api/car"
  constructor(private http:HttpClient) {}

  getAllCars():Observable<any>{
    return this.http.get(this.url)
  }

  addCar(commercialRegistrationNumber: string,carData: any): Observable<any> {
    return this.http.post(`${this.url}/${commercialRegistrationNumber}`, carData);
  }

  getCars(params: any): Observable<any> {
    let httpParams = new HttpParams();
    for (const key in params) {
      if (params[key]) {
        httpParams = httpParams.set(key, params[key]);
      }
    }
    return this.http.get<any>(this.url, { params: httpParams });
  }
  
}

