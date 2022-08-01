import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
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

  @Input('model') model!: any;
  @Output('onSelect') onSelect = new EventEmitter<any>();
  @Output('enterKeyPressed') enterKeyPressed = new EventEmitter<any>();
  @Output('arrowUpKeyPressed') arrowUpKeyPressed = new EventEmitter<any>();
  @Output('arrowDownKeyPressed') arrowDownKeyPressed = new EventEmitter<any>();
  @Output('arrowRightPressed') arrowRightPressed = new EventEmitter<any>();
  @Output('arrowLeftPressed') arrowLeftPressed = new EventEmitter<any>();
  private readonly formGroup: FormGroup;
  appearance: string = environment.appearance;
  private objectList?: Array<any>;
  private valueSelected: any;



  constructor(private fb: FormBuilder, private searchService: SearchAutocompleteService) {
    this.formGroup = this.fb.group({
      name: ['']
    });
  }

  ngOnInit(): void {

  }

  textChange(text: string) {
    //extract key from enum -- value from left
    let model = Object.keys(ModelSelectedEnum)[Object.values(ModelSelectedEnum).indexOf(this.model)];
    if(text?.length > 1) {
      if(isNaN(Number(text))) {
        this.searchService.searchText(model, text).subscribe({
          next: (data) => {
            this.loadList = data.content;
          }
        });
      } else {
        this.searchService.searchNumber(model, text).subscribe({
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
    this.onSelect.emit(element);
    this.valueSelected = element;
    console.log('optionSelect: ' + element);
  }

  valueFromInput(): string {
    return this.valueSelected?.name ?? '';
  }

  keyEnterPressed(event: any) {
    this.enterKeyPressed.emit();
    console.info(event);
  }

  keyArrowUpPressed($event: any) {
    this.arrowDownKeyPressed.emit();
    console.info($event);
  }

  keyArrowRightPressed($event: any) {
    this.arrowRightPressed.emit();
    console.info($event);
  }

  keyArrowDownPressed($event: any) {
    this.arrowDownKeyPressed.emit();
    console.info($event);
  }

  keyArrowLeftPressed($event: any) {
    this.arrowLeftPressed.emit();
    console.info($event);
  }
}
