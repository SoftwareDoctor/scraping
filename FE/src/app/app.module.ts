import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
// import { RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { LayoutComponent } from './components/layout/layout.component';
import { HomeComponent } from './pages/home/home.component';
import { CreateJoblinkComponent } from './pages/create-joblink/create-joblink.component';
import { JobListingItemComponent } from './components/job-listing-item/job-listing-item.component';
import { UpdateJoblistingComponent } from './pages/update-joblisting/update-joblisting.component';
import { ConfirmationDialogComponent } from './components/confirmation-dialog/confirmation-dialog.component';
import { SearchByNameComponent } from './pages/search-by-name/search-by-name.component';

@NgModule({
  declarations: [
    AppComponent,
    LayoutComponent,
    HomeComponent,
    CreateJoblinkComponent,
    JobListingItemComponent,
    UpdateJoblistingComponent,
    ConfirmationDialogComponent,
    SearchByNameComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    RouterModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
