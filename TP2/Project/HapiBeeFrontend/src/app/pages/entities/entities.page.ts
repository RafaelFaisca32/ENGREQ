import { Component } from '@angular/core';
import { NavController } from '@ionic/angular';
import { NavigationExtras } from '@angular/router';

@Component({
  selector: 'app-entities',
  templateUrl: 'entities.page.html',
  styleUrls: ['entities.page.scss'],
})
export class EntitiesPage {
  entities: Array<any> = [
    { name: 'Criar um Apiário', component: 'ApiaryPage', route: 'apiary/new', subroute:'entities' },
    { name: 'Lista de Apiários', component: 'ApiaryPage', route:'apiary', subroute: 'entities' },
    { name: 'Efetuar uma inspeção', component: 'ApiaryPage', route: 'apiary' , subroute:'entities', from: 'createInspection'},
    { name: 'Realizar a Cresta', component: 'ApiaryPage', route: 'apiary',  subroute:'entities', from: 'createCrest' },
    { name: 'Lista de Crestas', component: 'ApiaryPage', route: 'crest',  subroute:'entities', from: 'listCrest' },
    { name: 'Realizar um Desdobramento', component: 'ApiaryPage', route: 'apiary',  subroute:'entities', from: 'createUnfolding' },
    { name: 'Lista de Desdobramentos', component: 'ApiaryPage', route: 'unfolding',  subroute:'entities' },
    { name: 'Pedido de Transumância', component: 'ApiaryPage', route:'transhumance-request/new', subroute: 'entities' },
    { name: 'Lista de Pedidos de Transumância', component: 'ApiaryPage', route:'transhumance-request', subroute: 'entities' },
    { name: 'Submissão da declaração anual de existências', component: 'ApiaryPage', route:'annual-inventory-declaration/new', subroute: 'entities' },
    { name: 'Lista de declarações anuais de existências', component: 'ApiaryPage', route:'annual-inventory-declaration', subroute: 'entities' },
    { name: 'Adicionar uma Zona', component: 'ApiaryPage', route:'zone/new', subroute: 'entities' },
  ];

  constructor(public navController: NavController) {}

  openPage(page) {
    let navigationExtras : NavigationExtras;
    if(page.from){
      navigationExtras = { state : { from: page.from }, queryParams : { from: page.from } }
    }
    this.navController.navigateForward('/tabs/'+page.subroute+'/' + page.route, navigationExtras);

  }
}
