import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LayoutComponent } from './components/layout/layout.component';
import { HomeComponent } from './pages/home/home.component';
import { CreateJoblinkComponent } from './pages/create-joblink/create-joblink.component';
import { JobListingComponent } from './pages/job-listing/job-listing.component';
import { UpdateJoblistingComponent } from './pages/update-joblisting/update-joblisting.component';
import { ConfirmationDialogComponent } from './components/confirmation-dialog/confirmation-dialog.component';
import { SearchByNameComponent } from './pages/search-by-name/search-by-name.component';
import { ReactiveFormsModule } from '@angular/forms';
import { SinglejoblistingComponent } from './pages/singlejoblisting/singlejoblisting.component';

@NgModule({
  declarations: [
    AppComponent,
    LayoutComponent,
    HomeComponent,
    CreateJoblinkComponent,
    JobListingComponent,
    UpdateJoblistingComponent,
    ConfirmationDialogComponent,
    SearchByNameComponent,
    SinglejoblistingComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
