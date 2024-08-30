import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './components/layout/layout.component';
import { HomeComponent } from './pages/home/home.component';
import { CreateJoblinkComponent } from './pages/create-joblink/create-joblink.component';
import { JobListingItemComponent } from './components/job-listing-item/job-listing-item.component';
import { UpdateJoblistingComponent } from './pages/update-joblisting/update-joblisting.component';
import { SearchByNameComponent } from './pages/search-by-name/search-by-name.component';
import { ConfirmationDialogComponent } from './components/confirmation-dialog/confirmation-dialog.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },

  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: 'home', component: HomeComponent },
      { path: 'spy/search/:name', component: SearchByNameComponent },
      { path: 'create', component: CreateJoblinkComponent },
      { path: 'spy/all', component: JobListingItemComponent },
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
