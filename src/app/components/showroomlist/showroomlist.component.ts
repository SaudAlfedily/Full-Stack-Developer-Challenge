import { Component } from '@angular/core';
import { ShowroomService } from '../../services/showroom.service';

@Component({
  selector: 'app-showroomlist',
  templateUrl: './showroomlist.component.html',
  styleUrl: './showroomlist.component.css'
})export class ShowroomlistComponent  {
  showrooms: any[] = []; 
  displayedShowrooms: any[] = []; 
  totalElements = 0;
  pageIndex = 1;
  pageSize = 10;
  sortBy = 'name';
  sortOrder = 'asc';
  loading = false;

  constructor(private showroomService: ShowroomService) {}

  ngOnInit(): void {
    this.fetchShowrooms();
  }

 
  fetchShowrooms(): void {
    this.loading = true;
    this.showroomService
      .getShowrooms(this.pageIndex - 1, this.pageSize, this.sortBy, this.sortOrder)
      .subscribe(
        (data) => {
          this.showrooms = data.content;
          this.totalElements = data.totalElements;
          this.loading = false;
        },
        (error) => {
          console.error('Error fetching showrooms:', error);
          this.loading = false;
        }
      );
  }

 
  onPageChange(index: number): void {
    this.pageIndex = index;
    this.fetchShowrooms();
  }

  sort(column: string, order: string): void {
    this.sortBy = column;
    this.sortOrder = order === 'ascend' ? 'asc' : 'desc';
    this.fetchShowrooms();
  }


  deleteShowroom(id: number): void {
    this.loading = true;
    this.showroomService.deleteShowroom(id).subscribe(() => {
      this.fetchShowrooms();
      console.log(`Deleted showroom ${id}`);
    });
  }

}
