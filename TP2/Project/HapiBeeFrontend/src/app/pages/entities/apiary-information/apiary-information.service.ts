import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiService } from '../../../services/api/api.service';
import { createRequestOption } from '../../../shared';
import { ApiaryInformation } from './apiary-information.model';

@Injectable({ providedIn: 'root' })
export class ApiaryInformationService {
  private resourceUrl = ApiService.API_URL + '/apiary-informations';

  constructor(protected http: HttpClient) {}

  create(apiaryInformation: ApiaryInformation): Observable<HttpResponse<ApiaryInformation>> {
    return this.http.post<ApiaryInformation>(this.resourceUrl, apiaryInformation, { observe: 'response' });
  }

  update(apiaryInformation: ApiaryInformation): Observable<HttpResponse<ApiaryInformation>> {
    return this.http.put(`${this.resourceUrl}/${apiaryInformation.id}`, apiaryInformation, { observe: 'response' });
  }

  find(id: number): Observable<HttpResponse<ApiaryInformation>> {
    return this.http.get(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<HttpResponse<ApiaryInformation[]>> {
    const options = createRequestOption(req);
    return this.http.get<ApiaryInformation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
