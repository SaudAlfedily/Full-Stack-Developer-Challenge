
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule, provideHttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { ShowroomformComponent } from './components/showroomform/showroomform.component';
import { HomeComponent } from './components/home/home.component';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { en_US } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { NzDemoFormLayoutComponent } from './nz-demo-form-layout/nz-demo-form-layout.component';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzRadioModule } from 'ng-zorro-antd/radio';
import { ReactiveFormsModule } from '@angular/forms';
import { ShowroomlistComponent } from './components/showroomlist/showroomlist.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';

// NG-ZORRO Modules
import { NzTableModule } from 'ng-zorro-antd/table';
import { NzPaginationModule } from 'ng-zorro-antd/pagination';
import { NzCardModule } from 'ng-zorro-antd/card';
import { SingleShowromComponent } from './components/single-showrom/single-showrom.component';
// NG-ZORRO Modules
import { NzTagModule } from 'ng-zorro-antd/tag';
import { NzSpinModule } from 'ng-zorro-antd/spin';
import { NzDescriptionsModule } from 'ng-zorro-antd/descriptions';
import { UpdateShowromComponent } from './components/update-showrom/update-showrom.component';
import { AddCarComponent } from './components/add-car/add-car.component';
import { CarsInShowroomComponent } from './components/cars-in-showroom/cars-in-showroom.component';

registerLocaleData(en);

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    NzDemoFormLayoutComponent,
    ShowroomformComponent,
    ShowroomlistComponent,
    SingleShowromComponent,
    UpdateShowromComponent,
    AddCarComponent,
    CarsInShowroomComponent,
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
      NzButtonModule, // Required for buttons
    NzFormModule,   // Required for nz-form-item, nz-form-control, etc.
    NzInputModule,  // Required for input fields
    NzRadioModule   // Required for radio buttons,  FormsModule, // For template-driven forms (if needed)
    ,ReactiveFormsModule // For reactive forms NzTableModule,
    ,
    NzPaginationModule,
    NzCardModule,
    NzTableModule, 
    RouterModule,  
    NzTagModule,  // NG-ZORRO tag
    NzSpinModule, // NG-ZORRO spinner
    NzDescriptionsModule, // NG-ZORRO descriptions
  ],
  providers: [
    { provide: NZ_I18N, useValue: en_US },
    provideAnimationsAsync(),
    provideHttpClient()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
