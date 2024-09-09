import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-listatech',
  templateUrl: './listatech.component.html',
  styleUrls: ['./listatech.component.css']
})
export class ListatechComponent {
  selectedTechnology: string = '';

  constructor(
    public dialogRef: MatDialogRef<ListatechComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { technologies: { nameTechnology: string }[] }
  ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  onSelect(): void {
    this.dialogRef.close(this.selectedTechnology);
  }
}
