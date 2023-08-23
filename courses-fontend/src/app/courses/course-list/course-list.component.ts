import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Course } from '../model/course';

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.scss'],
})
export class CourseListComponent implements OnInit {
  @Input() courses: Course[] = [];
  @Output('onAddEventEmitter') onAddEventEmitter = new EventEmitter(false);

  readonly displayedColumns = ['name', 'category', 'actions'];

  constructor() {}

  ngOnInit(): void {}

  onAdd() {
    this.onAddEventEmitter.emit(true);
  }
}
