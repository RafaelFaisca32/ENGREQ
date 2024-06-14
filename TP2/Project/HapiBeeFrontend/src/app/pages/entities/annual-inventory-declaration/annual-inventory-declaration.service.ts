import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiService } from '../../../services/api/api.service';
import { createRequestOption } from '../../../shared';
import { AnnualInventoryDeclaration } from './annual-inventory-declaration.model';

@Injectable({ providedIn: 'root' })
export class AnnualInventoryDeclarationService {
  private resourceUrl = ApiService.API_URL + '/annual-inventory-declarations';

  constructor(protected http: HttpClient) {}

  create(annualInventoryDeclaration: AnnualInventoryDeclaration): Observable<HttpResponse<AnnualInventoryDeclaration>> {
    return this.http.post<AnnualInventoryDeclaration>(this.resourceUrl, annualInventoryDeclaration, { observe: 'response' });
  }

  update(annualInventoryDeclaration: AnnualInventoryDeclaration): Observable<HttpResponse<AnnualInventoryDeclaration>> {
    return this.http.put(`${this.resourceUrl}/${annualInventoryDeclaration.id}`, annualInventoryDeclaration, { observe: 'response' });
  }

  find(id: number): Observable<HttpResponse<AnnualInventoryDeclaration>> {
    return this.http.get(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<HttpResponse<AnnualInventoryDeclaration[]>> {
    const options = createRequestOption(req);
    return this.http.get<AnnualInventoryDeclaration[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
