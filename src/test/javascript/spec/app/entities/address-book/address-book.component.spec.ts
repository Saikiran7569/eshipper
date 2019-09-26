import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { AddressBookComponent } from 'app/entities/address-book/address-book.component';
import { AddressBookService } from 'app/entities/address-book/address-book.service';
import { AddressBook } from 'app/shared/model/address-book.model';

describe('Component Tests', () => {
  describe('AddressBook Management Component', () => {
    let comp: AddressBookComponent;
    let fixture: ComponentFixture<AddressBookComponent>;
    let service: AddressBookService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [AddressBookComponent],
        providers: []
      })
        .overrideTemplate(AddressBookComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AddressBookComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AddressBookService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new AddressBook(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.addressBooks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
