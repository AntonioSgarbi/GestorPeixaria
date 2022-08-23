import {MatSnackBar} from "@angular/material/snack-bar";
import {Injectable} from "@angular/core";

@Injectable({providedIn: 'root'})
export class AppService {
  constructor(private snackBar: MatSnackBar) {
  }

  showMessage(message: string, action: string): void {
    this.snackBar.open(message, action, {})
  }

}
