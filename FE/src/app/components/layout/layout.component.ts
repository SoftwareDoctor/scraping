import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'layout',
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.css'
})
export class LayoutComponent {
 title = 'Software Doctor';
 subtitle = 'Andrea Italiano';

    constructor(private router: Router) { }

      navigateToHome(): void {
        this.router.navigate(['/']);
      }
}
