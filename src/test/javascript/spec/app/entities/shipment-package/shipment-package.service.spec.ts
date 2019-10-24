import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { ShipmentPackageService } from 'app/entities/shipment-package/shipment-package.service';
import { IShipmentPackage, ShipmentPackage } from 'app/shared/model/shipment-package.model';

describe('Service Tests', () => {
  describe('ShipmentPackage Service', () => {
    let injector: TestBed;
    let service: ShipmentPackageService;
    let httpMock: HttpTestingController;
    let elemDefault: IShipmentPackage;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(ShipmentPackageService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new ShipmentPackage(0, 'AAAAAAA', 0, 0, 0, 0, 0, 'AAAAAAA', 0, 0, 0, 'AAAAAAA', 'AAAAAAA', 0, 0, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a ShipmentPackage', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new ShipmentPackage(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a ShipmentPackage', () => {
        const returnedFromService = Object.assign(
          {
            description: 'BBBBBB',
            length: 1,
            width: 1,
            height: 1,
            weight: 1,
            position: 1,
            trackingNumber: 'BBBBBB',
            cubedWeight: 1,
            codValue: 1,
            insuranceAmount: 1,
            freightClass: 'BBBBBB',
            nmfcCode: 'BBBBBB',
            weightOz: 1,
            itemValue: 1,
            harmonizedCode: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of ShipmentPackage', () => {
        const returnedFromService = Object.assign(
          {
            description: 'BBBBBB',
            length: 1,
            width: 1,
            height: 1,
            weight: 1,
            position: 1,
            trackingNumber: 'BBBBBB',
            cubedWeight: 1,
            codValue: 1,
            insuranceAmount: 1,
            freightClass: 'BBBBBB',
            nmfcCode: 'BBBBBB',
            weightOz: 1,
            itemValue: 1,
            harmonizedCode: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
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

      it('should delete a ShipmentPackage', () => {
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
