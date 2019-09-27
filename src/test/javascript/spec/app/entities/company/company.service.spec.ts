import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { CompanyService } from 'app/entities/company/company.service';
import { ICompany, Company } from 'app/shared/model/company.model';

describe('Service Tests', () => {
  describe('Company Service', () => {
    let injector: TestBed;
    let service: CompanyService;
    let httpMock: HttpTestingController;
    let elemDefault: ICompany;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(CompanyService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Company(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        currentDate,
        0,
        'AAAAAAA',
        false,
        0
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateCreated: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a Company', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateCreated: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dateCreated: currentDate
          },
          returnedFromService
        );
        service
          .create(new Company(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Company', () => {
        const returnedFromService = Object.assign(
          {
            accountNumber: 'BBBBBB',
            name: 'BBBBBB',
            address1: 'BBBBBB',
            address2: 'BBBBBB',
            postalCode: 'BBBBBB',
            phone: 'BBBBBB',
            email: 'BBBBBB',
            timeZone: 'BBBBBB',
            costAccount: 1,
            dateCreated: currentDate.format(DATE_TIME_FORMAT),
            creator: 1,
            contact: 'BBBBBB',
            isShopifyEnable: true,
            defaultSignatureOption: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateCreated: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of Company', () => {
        const returnedFromService = Object.assign(
          {
            accountNumber: 'BBBBBB',
            name: 'BBBBBB',
            address1: 'BBBBBB',
            address2: 'BBBBBB',
            postalCode: 'BBBBBB',
            phone: 'BBBBBB',
            email: 'BBBBBB',
            timeZone: 'BBBBBB',
            costAccount: 1,
            dateCreated: currentDate.format(DATE_TIME_FORMAT),
            creator: 1,
            contact: 'BBBBBB',
            isShopifyEnable: true,
            defaultSignatureOption: 1
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dateCreated: currentDate
          },
          returnedFromService
        );
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Company', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
