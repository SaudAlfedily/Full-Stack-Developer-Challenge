import { Injectable } from '@angular/core';
import { HttpClient ,HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Showroom } from '../models/showroom.model';


interface Page<T> {
  content: T[];
  pageable: { pageNumber: number; pageSize: number };
  totalPages: number;
  totalElements: number;
}

@Injectable({
  providedIn: 'root'
})
export class ShowroomService {
  readonly url:string = "http://localhost:8080/api/showrooms"

  constructor(private http:HttpClient) { 
    
  }
  creatCarShowroom(showroom: Showroom):Observable<any>{
    return this.http.post(this.url,showroom)
  }
  getShowrooms(page: number, size: number, sortBy: string, sortOrder: string): Observable<any> {
    return this.http.get(`${this.url}?page=${page}&size=${size}&sortBy=${sortBy}&sortOrder=${sortOrder}`);
  }
  getShowroomDetails(commercialRegistrationNumber: string): Observable<Showroom> {
    
    return this.http.get<Showroom>(`${this.url}/${commercialRegistrationNumber}`);
  }
  deleteShowroom(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }
  updateShowroomDetails(commercialRegistrationNumber: string, updatedData: Partial<Showroom>): Observable<void> {
    return this.http.put<void>(`${this.url}/${commercialRegistrationNumber}`, updatedData);
  }

}

