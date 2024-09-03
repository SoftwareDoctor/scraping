import { Component, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JobListing } from '../../models/joblisting/joblisting';

@Component({
  selector: 'app-confirmation-dialog',
  templateUrl: './confirmation-dialog.component.html',
  styleUrls: ['./confirmation-dialog.component.css']
})
export class ConfirmationDialogComponent {
  @Input() jobListing: JobListing | null = null;

  constructor(public activeModal: NgbActiveModal) { }

  public onConfirm(): void {
    // Chiudi la modale e restituisci 'yes'
    this.activeModal.close('yes');
  }

  public onCancel(): void {
    // Chiudi la modale senza conferma
    this.activeModal.dismiss('no');
  }
}
