import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CarApiService } from '../../services/car-api.service';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-add-car',
  templateUrl: './add-car.component.html',
  styleUrl: './add-car.component.css'
})
export class AddCarComponent {
  addCarForm: FormGroup;
  commercialRegistrationNumber: string = ''; 

  
  constructor(private fb: FormBuilder, private carService: CarApiService, private route: ActivatedRoute,
    private router: Router) {
    this.addCarForm = this.fb.group(
      {
      vin: ['', [Validators.required, Validators.maxLength(25)]],
      maker: ['', [Validators.required, Validators.maxLength(25)]],
      model: ['', [Validators.required, Validators.maxLength(25)]],
      modelYear: ['', [Validators.required, Validators.min(1886)]],
      price: ['', [Validators.required, Validators.min(0.01)]]
      
     
    });
  }
 ngOnInit(): void {
    
    this.commercialRegistrationNumber = this.route.snapshot.paramMap.get('commercialRegistrationNumber') || '';
    this.onSubmit();
  }
  onSubmit(): void {
    if (this.addCarForm.valid) {
      const carData = this.addCarForm.value;
      this.carService.addCar(this.commercialRegistrationNumber,carData).subscribe({
        next: (response) => {
          console.log('Car added successfully:', response);
          alert('Car added successfully!');
          this.addCarForm.reset(); // Reset form after success
        },
        error: (err) => {
          console.error('Error adding car:', err);
          alert('Failed to add car. Please try again.');
        }
      });
    } 
  }
}
