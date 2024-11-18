import { Component } from '@angular/core';
import { Showroom } from '../../models/showroom.model';
import { ShowroomService } from '../../services/showroom.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-single-showrom',
  templateUrl: './single-showrom.component.html',
  styleUrls: ['./single-showrom.component.css']
})
export class SingleShowromComponent {
  showroom?: Showroom; 
  errorMessage = ''; 

  constructor(
    private route: ActivatedRoute,
    private showroomService: ShowroomService
  ) {}

  ngOnInit(): void {
    // Extract commercialRegistrationNumber from route params
    const commercialRegistrationNumber = this.route.snapshot.paramMap.get('commercialRegistrationNumber');
    if (commercialRegistrationNumber) {
      this.fetchShowroomDetails(commercialRegistrationNumber);
    }
  }

  // Fetch showroom details
  fetchShowroomDetails(commercialRegistrationNumber: string): void {
    
    this.showroomService.getShowroomDetails(commercialRegistrationNumber).subscribe({
      next: (data) => {
        this.showroom = data;
      },
      error: (err) => {
        console.error('Error fetching showroom details:', err);
        this.errorMessage = 'Failed to load showroom details. Please try again later.';
      },
    });
  }
}

