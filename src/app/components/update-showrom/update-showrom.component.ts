import { Component } from '@angular/core';
import { ShowroomService } from '../../services/showroom.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Showroom } from '../../models/showroom.model';

@Component({
  selector: 'app-update-showrom',
  templateUrl: './update-showrom.component.html',
  styleUrl: './update-showrom.component.css'
})
export class UpdateShowromComponent {
  updateForm: FormGroup;
  commercialRegistrationNumber: string = ''; 
  errorMessage: string = '';
  successMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private showroomService: ShowroomService,
    private router: Router
  ) {
    this.updateForm = this.fb.group({
      managerName: ['', [Validators.maxLength(100)]],
      contactNumber: ['', [Validators.required, Validators.pattern('^[0-9]{1,15}$')]],
      address: ['', [Validators.maxLength(255)]]
    });
  }

  ngOnInit(): void {
    
    this.commercialRegistrationNumber = this.route.snapshot.paramMap.get('commercialRegistrationNumber') || '';
    this.loadShowroomDetails();
  }


  loadShowroomDetails(): void {
    this.showroomService.getShowroomDetails(this.commercialRegistrationNumber).subscribe({
      next: (showroom: Showroom) => {
        this.updateForm.patchValue({
          managerName: showroom.managerName,
          contactNumber: showroom.contactNumber,
          address: showroom.address
        });
      },
      error: (err) => {
        console.error('Error fetching showroom details:', err);
        this.errorMessage = 'Failed to load showroom details.';
      }
    });
  }

 
  onSubmit(): void {
    if (this.updateForm.valid) {
      const updatedData = this.updateForm.value;
      this.showroomService.updateShowroomDetails(this.commercialRegistrationNumber, updatedData).subscribe({
        next: () => {
          this.successMessage = 'Showroom details updated successfully!';
          setTimeout(() => this.router.navigate(['/showrooms']), 2000); 
        },
        error: (err) => {
          console.error('Error updating showroom details:', err);
          this.errorMessage = 'Failed to update showroom details.';
        }
      });
    }
  }
}
