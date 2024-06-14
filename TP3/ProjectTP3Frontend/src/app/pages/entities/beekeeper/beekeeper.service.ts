import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiService } from '../../../services/api/api.service';
import { createRequestOption } from '../../../shared';
import { Beekeeper } from './beekeeper.model';

@Injectable({ providedIn: 'root' })
export class BeekeeperService {
  private resourceUrl = ApiService.API_URL + '/beekeepers';

  constructor(protected http: HttpClient) {}

  create(beekeeper: Beekeeper): Observable<HttpResponse<Beekeeper>> {
    return this.http.post<Beekeeper>(this.resourceUrl, beekeeper, { observe: 'response' });
  }

  update(beekeeper: Beekeeper): Observable<HttpResponse<Beekeeper>> {
    return this.http.put(`${this.resourceUrl}/${beekeeper.id}`, beekeeper, { observe: 'response' });
  }

  find(id: number): Observable<HttpResponse<Beekeeper>> {
    return this.http.get(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<HttpResponse<Beekeeper[]>> {
    const options = createRequestOption(req);
    return this.http.get<Beekeeper[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
