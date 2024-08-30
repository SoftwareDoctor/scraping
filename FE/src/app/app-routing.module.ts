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
  { path: '', redirectTo: 'home', pathMatch: 'full' }, // Reindirizza a 'home' direttamente

  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: 'home', component: HomeComponent },
      { path: 'search/name/:name', component: SearchByNameComponent },
      { path: 'jobs/new', component: CreateJoblinkComponent },
      { path: 'jobs/all', component: JobListingItemComponent },
      { path: 'jobs/edit/:uuid', component: UpdateJoblistingComponent }
    ]
  },

  { path: 'confirm', component: ConfirmationDialogComponent },
  { path: '**', redirectTo: '' } // Gestisce tutte le rotte non valide
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
