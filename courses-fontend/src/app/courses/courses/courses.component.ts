import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { catchError, first, Observable, of, tap } from 'rxjs';
import { ErrorDialogComponent } from 'src/app/shared/shared-components/components/error-dialog/error-dialog.component';

import { Course } from '../model/course';
import { CoursesService } from './../services/courses.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmationDialogComponent } from 'src/app/shared/shared-components/components/confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss'],
})
export class CoursesComponent implements OnInit {
  courses$: Observable<Course[]> | null = null;

  constructor(
    private service: CoursesService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private _snackBar: MatSnackBar
  ) {
    this.refresh();
  }

  onErrorMessage(error: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: error,
    });
  }

  ngOnInit(): void {}

  onAdd() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }

  onEdit(course: Course) {
    this.router.navigate(['edit', course._id], { relativeTo: this.route });
  }

  onDelete(id: string) {
    this.openDialog(id);
  }

  private deleteCourse(id: string) {
    this.service.deleteCourse(id).subscribe({
      next: (v) => {
        this.refresh();
        this._snackBar.open('Course was deleted!', '', {
          duration: 3000,
          verticalPosition: 'top',
          horizontalPosition: 'center',
        });
      },
      error: (e) => this.onErrorMessage("Course couldn't be removed!"),
    });
  }

  private refresh() {
    this.courses$ = this.service.getAllCourses().pipe(
      first(),
      tap((c) => console.log(c)),
      catchError((error) => {
        this.onErrorMessage('No data found!');
        return of([]);
      })
    );
  }

  private openDialog(id: string): void {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: 'Are you sure?',
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
        this.deleteCourse(id);
      }
    });
  }
}
