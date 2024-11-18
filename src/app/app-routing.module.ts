import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ShowroomlistComponent } from './components/showroomlist/showroomlist.component';
import { ShowroomformComponent } from './components/showroomform/showroomform.component';
import { SingleShowromComponent } from './components/single-showrom/single-showrom.component';
import { UpdateShowromComponent } from './components/update-showrom/update-showrom.component';
import { AddCarComponent } from './components/add-car/add-car.component';
import { CarsInShowroomComponent } from './components/cars-in-showroom/cars-in-showroom.component';
const routes: Routes = [
  { path: 'showrooms', component: ShowroomlistComponent },
  { path: 'showrooms/add', component: ShowroomformComponent },
  { path: '', redirectTo: '/showrooms', pathMatch: 'full' }, // Redirect default
  { path: 'showrooms/:commercialRegistrationNumber', component: SingleShowromComponent },
  { path: 'showrooms/update/:commercialRegistrationNumber', component: UpdateShowromComponent },
  { path: 'cars/add/:commercialRegistrationNumber', component: AddCarComponent },
  { path: 'showrooms/:id/cars', component: CarsInShowroomComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
