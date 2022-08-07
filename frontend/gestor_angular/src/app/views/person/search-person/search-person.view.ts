import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {SaleItem} from "../model/sale.model";
import {SearchPersonService} from "./search-person.service";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {Person} from "../model/person.model";
import {LegalRecordType} from "../model/legal.record.type.enum";
import {FormBuilder, FormGroup} from "@angular/forms";
import {MatDrawer} from "@angular/material/sidenav";

@Component({
  selector: 'app-stock-search-person',
  templateUrl: './search-person.view.html',
  styleUrls: ['./search-person.view.css']
})
export class PessoaPesquisaComponent implements OnInit, AfterViewInit {
  @ViewChild('paginator') paginator!: MatPaginator;
  @ViewChild('drawer') drawer!: MatDrawer;
  dataSource: MatTableDataSource<SaleItem> = new MatTableDataSource<SaleItem>([]);
  legalRecordType: any = LegalRecordType;
  displayedColumns: string[] = ['name', 'document', 'legalRecordType', 'email'];
  private readonly formGroup: FormGroup;
  personType: string = 'customer';

  get form(): FormGroup {
    return this.formGroup;
  }

  constructor(private searchService: SearchPersonService, private fb: FormBuilder) {
    this.formGroup = this.fb.group({
      legalRecordType: [null],
      name: [null],
      document: [null],
      email: [null]
    });
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

  setPage(page: PageEvent) {
    this.searchService.findAll(this.personType, page.pageIndex, page.pageSize).subscribe({
      next: (data) => {
        this.dataSource.data = data.content;
      }
    });
  }

  personClicked(index: number) {
    let person: Person = this.dataSource.data[index];

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
}
