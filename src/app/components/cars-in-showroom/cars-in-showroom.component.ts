import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CarApiService } from '../../services/car-api.service';

@Component({
  selector: 'app-cars-in-showroom',
  templateUrl: './cars-in-showroom.component.html',
  styleUrl: './cars-in-showroom.component.css'
})
export class CarsInShowroomComponent {
  cars: any[] = [];
  totalItems: number = 0;
  currentPage: number = 1;
  pageSize: number = 10;
  loading: boolean = false;
  filterForm: FormGroup;

  constructor(private carService: CarApiService, private fb: FormBuilder) {
  
    this.filterForm = this.fb.group({
      vin: [''],
      maker: [''],
      model: [''],
      price: [''],
      modelYear: [''],
      showroomName: [''],
      contactNumber: ['']
    });
  }

  ngOnInit(): void {
    this.fetchCars();

    this.filterForm.valueChanges.subscribe(() => {
      this.fetchCars();
    });
  }

  fetchCars(): void {
    this.loading = true;

    const filters = this.filterForm.value;
    const params = {
      page: this.currentPage - 1, 
      size: this.pageSize,
      vin: filters.vin || null,
      maker: filters.maker || null,
      model: filters.model || null,
      price: filters.price || null,
      model_year: filters.modelYear || null,
      showroom_name: filters.showroomName || null,
      contact_number: filters.contactNumber || null
    };

    this.carService.getCars(params).subscribe({
      next: (response) => {
        this.cars = response.content;
        this.totalItems = response.totalElements;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error fetching cars:', err);
        this.loading = false;
      }
    });
  }

  onPageChange(page: number): void {
    this.currentPage = page;
    this.fetchCars();
  }
}
