import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { catchError, first, Observable, of, tap } from 'rxjs';
import { ErrorDialogComponent } from 'src/app/shared/shared-components/components/error-dialog/error-dialog.component';

import { Course } from '../model/course';
import { CoursesService } from './../services/courses.service';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss'],
})
export class CoursesComponent implements OnInit {
  courses$: Observable<Course[]>;
  // displayedColumns = ['name', 'category', 'actions'];

  constructor(
    private service: CoursesService,
    public dialog: MatDialog // private router: Router,
  ) // private route: ActivatedRoute
  {
    this.courses$ = this.service.getAllCourses().pipe(
      first(),
      tap((c) => console.log(c)),
      catchError((error) => {
        this.onErrorMessage('No data found!');
        return of([]);
      })
    );
  }

  onErrorMessage(error: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: error,
    });
  }

  ngOnInit(): void {}

  // onAdd() {
  //   this.router.navigate(['new'], { relativeTo: this.route });
  // }
}
