import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {LegalRecordType} from "../../../model/legal.record.type.enum";
import {PersonService} from "../person.service";
import {AppService} from "../../../app.service";
import {RegistrationType} from "../../../model/registration.type.enum";
import {ActivatedRoute} from "@angular/router";
import {Person} from "../../../model/person.type";

@Component({
  selector: 'app-registration-person',
  templateUrl: './registration-person.view.html',
  styleUrls: ['./registration-person.view.css']
})
export class RegistrationPersonView implements OnInit {

  form: FormGroup = new FormGroup({});
  appearance: string = 'outline';
  registrationType: any = RegistrationType;
  legalRecordType: any = LegalRecordType;
  isEdit: boolean | undefined;

  constructor(
    private fb: FormBuilder,
    private appService: AppService,
    private personService: PersonService,
    private activeRouter: ActivatedRoute
  ) {
  }

  get phones(): FormArray {
    // @ts-ignore
    return this.form.get('phones')['controls'] as FormArray;
  }

  ngOnInit(): void {
    this.initForm();
    this.fillFormOnEdit();
  }

  private fillFormOnEdit() {
    if (this.activeRouter.snapshot.paramMap.get('id')) {
      this.isEdit = true;
      let personType = this.activeRouter.snapshot.paramMap.get('personType')!;

      this.form.get('registrationType')?.setValue(personType);

      let id = Number.parseInt(this.activeRouter.snapshot.paramMap.get('id')!);

      this.personService.findById(id, personType).subscribe({
        next: (res: Person) => {
          this.form.patchValue(res);
        },
        error: (ex) => {
          console.log(ex);
        },
      });
    }
  }


  initForm(): void {
    this.form = this.fb.group({
      "registrationType": [null, Validators.required],
      "name": [null, Validators.required],
      "legalRecordType": [null, Validators.required],
      "document": [],
      "email": [null, Validators.required],
      "birthDate": [],
      "phones": this.fb.array([this.fb.control(null, Validators.required)]),
    })
  }

  generatePhoneField(): FormControl {
    return new FormControl(null, Validators.required);
  }

  addPhoneNumber(): void {
    let telefones = this.form.get('phones') as FormArray;
    telefones.push(this.generatePhoneField());
  }

  removePhoneNumber(index: number): void {
    let phones = this.form.get('phones') as FormArray;
    phones.removeAt(index);
  }

  resetForm() {
    this.form.reset();
  }

  submit(): void {
    if (this.form.valid) {
      let type: RegistrationType = this.form.get('registrationType')?.value;
      console.error(type);
      console.info(this.form);

      this.personService.registerPerson(this.form.value, type).subscribe({
        next: (id: number) => {
          this.successSubmit();
        },
        error: (err: any) => {
          this.failSubmit();
        }
      });
    } else {
      this.appService.showMessage('Preencha todos os campos obrigatórios!', 'fechar')
    }
  }

  successSubmit() {
    this.form.reset();
    alert('Sucesso ao cadastrar!');
  }

  failSubmit() {
    this.appService.showMessage('Falha ao cadastrar, tente novamente!', 'fechar')
  }


  documentMask(): string {
    return this.form.get('legalRecordType')?.value == 'PESSOA_JURIDICA' ? '00.000.000/0000-00' : '000.000.000-00';
  }

  placeholderDocument(): string {
    return this.form.get('legalRecordType')?.value == 'PESSOA_JURIDICA' ? 'CNPJ' : 'CPF';
  }

  isIndividual(): boolean {
    return this.form.get('legalRecordType')?.value == 'PESSOA_FISICA';
  }

}
