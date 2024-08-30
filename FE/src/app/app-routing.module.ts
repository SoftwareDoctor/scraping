import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './components/layout/layout.component';
import { HomeComponent } from './pages/home/home.component';
import { CreateJoblinkComponent } from './pages/create-joblink/create-joblink.component';
import { UpdateJoblistingComponent } from './pages/update-joblisting/update-joblisting.component';
import { SearchByNameComponent } from './pages/search-by-name/search-by-name.component';
import { ConfirmationDialogComponent } from './components/confirmation-dialog/confirmation-dialog.component';
import { JobListingComponent } from './pages/job-listing/job-listing.component';
const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },

  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: 'home', component: HomeComponent },
      { path: 'search/:name', component: SearchByNameComponent },
      { path: 'create', component: CreateJoblinkComponent },
      { path: 'all', component: JobListingComponent },
      { path: 'spy/:uuid', component: UpdateJoblistingComponent }
    ]
  },

  { path: 'confirm', component: ConfirmationDialogComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
