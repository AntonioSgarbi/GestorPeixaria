import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {SaleItem} from "../../../model/sale.type";
import {SearchPersonService} from "./search-person.service";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {Person} from "../../../model/person.type";
import {LegalRecordType} from "../../../model/legal.record.type.enum";
import {FormBuilder, FormGroup} from "@angular/forms";
import {MatDrawer} from "@angular/material/sidenav";
import {Router} from "@angular/router";

@Component({
  selector: 'app-stock-search-person',
  templateUrl: './search-person.view.html',
  styleUrls: ['./search-person.view.css']
})
export class PessoaPesquisaComponent implements OnInit, AfterViewInit {
  @ViewChild('paginator') paginator!: MatPaginator;
  @ViewChild('drawer') drawer!: MatDrawer;
  dataSource: MatTableDataSource<Person> = new MatTableDataSource<Person>([]);
  legalRecordType: any = LegalRecordType;
  displayedColumns: string[] = ['name', 'document', 'legalRecordType', 'email'];
  personType: string = 'customer';
  private readonly formGroup: FormGroup;

  constructor(private searchService: SearchPersonService, private fb: FormBuilder, private router: Router) {
    this.formGroup = this.fb.group({
      legalRecordType: [null],
      name: [null],
      document: [null],
      email: [null]
    });
  }

  get form(): FormGroup {
    return this.formGroup;
  }

  ngOnInit(): void {
    this.searchService.findAll(this.personType).subscribe({
      next: (data) => {
        this.dataSource.data = data.content;
      }
    });
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  personClicked(index: number) {
    let person: Person = this.dataSource.data[index];

    this.router.navigate(['/person/registration-person', this.personType, person.id]);
  }

  filter() {
    this.drawer.toggle();
    let filter = this.form.value;
    this.searchService.findAllFiltered(this.personType, 0, 10, filter).subscribe({
      next: (data) => {
        this.dataSource.data = data.content;
      }
    });
  }

  setPage(page: PageEvent) {
    this.searchService.findAll(this.personType, page.pageIndex, page.pageSize).subscribe({
      next: (data) => {
        this.dataSource.data = data.content;
      }
    });
  }

}
