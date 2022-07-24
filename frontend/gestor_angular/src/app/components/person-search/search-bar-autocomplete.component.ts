import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {environment} from "../../../environments/environment";
import {ModelSelectedEnum} from "./model.selected.enum";
import {SearchAutocompleteService} from "./service/search.autocomplete.service";

@Component({
  selector: 'search-bar',
  templateUrl: './search-bar-autocomplete.component.html',
  styleUrls: ['./search-bar-autocomplete.component.css']
})
export class SearchBarAutocompleteComponent implements OnInit {
  get loadList(): Array<any> {
    return this.objectList!;
  }

  set loadList(value: Array<any>) {
    this.objectList = value;
  }

  get form(): FormGroup {
    return this.formGroup;
  }

  @Input('model') model!: string;
  private readonly formGroup: FormGroup;
  appearance: string = environment.appearance;
  private objectList?: Array<any>;

  constructor(private fb: FormBuilder, private searchService: SearchAutocompleteService) {
    this.formGroup = this.fb.group({
      name: ['']
    });
  }

  ngOnInit(): void {

  }

  textChange(text: string) {
    if(text.length > 1) {
      if(isNaN(Number(text))) {
        this.searchService.searchText(this.model, text).subscribe({
          next: (data) => {
            this.loadList = data.content;
          }
        });
      } else {
        this.searchService.searchNumber(this.model, text).subscribe({
          next: (data) => {
            this.loadList = data.content;
          }
        });
      }
    }
  }

  displayFn(item: any) {
    return item ? item.name : '';
  }

  optionSelect(element: any) {
    console.log(element);
  }

  valueInput() {
    return undefined;
  }
}
