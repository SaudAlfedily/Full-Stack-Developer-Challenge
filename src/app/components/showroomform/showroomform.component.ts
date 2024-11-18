import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ShowroomService } from '../../services/showroom.service'; 
import { Router } from '@angular/router'; 

@Component({
  selector: 'app-showroomform',
  templateUrl: './showroomform.component.html',
  styleUrl: './showroomform.component.css'
})
export class ShowroomformComponent {
  showroomForm: FormGroup;

  constructor(private fb: FormBuilder, private showroomService: ShowroomService, private router: Router) {
    this.showroomForm = this.fb.group({
      name: ['', [Validators.required, Validators.maxLength(100)]],
      commercialRegistrationNumber: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      managerName: ['', [Validators.maxLength(100)]],
      contactNumber: ['', [Validators.required, Validators.pattern('^[0-9]{1,15}$')]],
      address: ['', [Validators.maxLength(255)]],
      active: [true]
    });
  }

  onSubmit(): void {
    if (this.showroomForm.valid) {
      const showroomData = this.showroomForm.value;

      this.showroomService.creatCarShowroom(showroomData).subscribe({
        next: (response) => {
          console.log('Showroom created successfully:', response);
          alert('Showroom created successfully!');
          this.router.navigate(['/']);
        },
        error: (err) => {
          console.error('Error creating showroom:', err);
          alert('Failed to create showroom. Please try again.');
        }
      });
    } else {
      console.error('Form is invalid');
    }
  }
}
