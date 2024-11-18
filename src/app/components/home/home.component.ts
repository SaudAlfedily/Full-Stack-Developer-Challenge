import { Component, OnInit } from '@angular/core';
import { Car } from '../../models/car.model';
import { CarApiService } from '../../services/car-api.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{
cars:Car[] = []
constructor(private carApi:CarApiService){
}
  ngOnInit(): void {
    this.getAllCars()
  }

  getAllCars(){
    return this.carApi.getAllCars().subscribe({
      next: (car)=>{
        this.cars = car.content
        console.log(this.cars)
      },
    })
  }
}
