import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiService } from '../../../services/api/api.service';
import { createRequestOption } from '../../../shared';
import { Disease } from './disease.model';

@Injectable({ providedIn: 'root' })
export class DiseaseService {
  private resourceUrl = ApiService.API_URL + '/diseases';

  constructor(protected http: HttpClient) {}

  create(disease: Disease): Observable<HttpResponse<Disease>> {
    return this.http.post<Disease>(this.resourceUrl, disease, { observe: 'response' });
  }

  update(disease: Disease): Observable<HttpResponse<Disease>> {
    return this.http.put(`${this.resourceUrl}/${disease.id}`, disease, { observe: 'response' });
  }

  find(id: number): Observable<HttpResponse<Disease>> {
    return this.http.get(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<HttpResponse<Disease[]>> {
    const options = createRequestOption(req);
    return this.http.get<Disease[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
