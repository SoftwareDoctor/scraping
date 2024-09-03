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
    SearchbynameComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule,
    AppRoutingModule,
    ReactiveFormsModule,
    NgbModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
