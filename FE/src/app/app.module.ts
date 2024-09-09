import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LayoutComponent } from './components/layout/layout.component';
import { HomeComponent } from './pages/home/home.component';
import { CreateJoblinkComponent } from './pages/create-joblink/create-joblink.component';
import { JobListingComponent } from './pages/job-listing/job-listing.component';
import { UpdateJoblistingComponent } from './pages/update-joblisting/update-joblisting.component';
import { ConfirmationDialogComponent } from './components/confirmation-dialog/confirmation-dialog.component';
import { SinglejoblistingComponent } from './pages/singlejoblisting/singlejoblisting.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {SearchbynameComponent } from './pages/searchbyname/searchbyname.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSelectModule } from '@angular/material/select';
import {ListatechComponent } from './components/listatech/listatech.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { MatIconModule } from '@angular/material/icon';
import { MatChipsModule } from '@angular/material/chips';

@NgModule({
  declarations: [
    AppComponent,
    LayoutComponent,
    HomeComponent,
    CreateJoblinkComponent,
    JobListingComponent,
    UpdateJoblistingComponent,
    ConfirmationDialogComponent,
    SinglejoblistingComponent,
    SearchbynameComponent,
    ListatechComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule,
    AppRoutingModule,
    ReactiveFormsModule,
    NgbModule,
    FormsModule,
    MatDialogModule,
    MatSelectModule,
    MatIconModule,
    MatChipsModule
  ],
  providers: [
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
