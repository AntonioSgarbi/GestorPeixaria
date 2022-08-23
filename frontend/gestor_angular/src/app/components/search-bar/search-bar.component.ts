import {Component, EventEmitter, forwardRef, Input, OnInit, Output} from '@angular/core';
import {ControlValueAccessor, FormBuilder, FormControl, FormGroup, NG_VALUE_ACCESSOR} from "@angular/forms";
import {environment} from "../../../environments/environment";
import {ModelSelectedEnum} from "../../model/model.selected.enum";
import {SearchBarService} from "./search-bar.service";

@Component({
  selector: 'search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => SearchBarComponent),
      multi: true
    }
  ]
})
export class SearchBarComponent implements ControlValueAccessor, OnInit {

  @Input('model') model!: any;
  @Input('placeholder') placeholder!: string;

  @Output('onSelect') onSelect = new EventEmitter<any>();
  @Output('enterKeyPressed') enterKeyPressed = new EventEmitter<any>();
  @Output('arrowUpKeyPressed') arrowUpKeyPressed = new EventEmitter<any>();
  @Output('arrowDownKeyPressed') arrowDownKeyPressed = new EventEmitter<any>();
  @Output('arrowRightPressed') arrowRightPressed = new EventEmitter<any>();
  @Output('arrowLeftPressed') arrowLeftPressed = new EventEmitter<any>();
  appearance: string = environment.appearance;
  private readonly formGroup: FormGroup;
  private objectList?: Array<any>;
  private _valueSelected: any;

  constructor(private fb: FormBuilder, private searchService: SearchBarService) {
    this.formGroup = this.fb.group({
      name: ['']
    });
  }

  get loadList(): Array<any> {
    return this.objectList!;
  }

  set loadList(value: Array<any>) {
    this.objectList = value;
  }

  get form(): FormGroup {
    return this.formGroup;
  }

  get getValueSelected(): any {
    return this._valueSelected ?? '';
  }

  onChange: any = () => {
  };

  onTouched: any = () => {
  };

  ngOnInit(): void {
    this.formGroup.addControl(this.getKeyFromEnum(), new FormControl());
  }

  setValueSelected(value: any | null) {
    this.formGroup.get(this.getKeyFromEnum())!.setValue(value);
    this._valueSelected = value;
  }

  writeValue(obj: any): void {
    this.form.get(this.getKeyFromEnum())!.setValue(obj);
    this._valueSelected = obj;
  }

  registerOnChange(fn: any): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }

  getKeyFromEnum(): string {
    // return value from left { 'collaborator' | 'customer' | 'product' | 'supplier' }
    return Object.keys(ModelSelectedEnum)[Object.values(ModelSelectedEnum).indexOf(this.model)];
  }

  textChange(text: string) {
    let model = this.getKeyFromEnum();
    if (text?.length > 1) {
      if (isNaN(Number(text))) {
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

  displayFn(item: any): string {
    return item ? item.name : '';
  }

  optionSelect(element: any): void {
    this.onSelect.emit(element);
    this._valueSelected = element;
  }

  valueFromInput(): string {
    return this._valueSelected?.name ?? '';
  }

  keyEnterPressed(event: any): void {
    this.enterKeyPressed.emit();
    console.info(event);
  }

  keyArrowUpPressed($event: any): void {
    console.info(this.form);
    this.arrowDownKeyPressed.emit();
    console.info($event);
  }

  keyArrowRightPressed($event: any): void {
    this.arrowRightPressed.emit();
    console.info($event);
  }

  keyArrowDownPressed($event: any): void {
    this.arrowDownKeyPressed.emit();
    console.info($event);
  }

  keyArrowLeftPressed($event: any): void {
    this.arrowLeftPressed.emit();
    console.info($event);
  }

  dropSelectedValue(): void {
    this.onSelect.emit(null);
    this._valueSelected = null;
  }

  isObjectNotNull(): boolean {
    return this._valueSelected != null;
  }

  keyBackspacePressed(event: any) {
    this.dropSelectedValue();
  }
}
