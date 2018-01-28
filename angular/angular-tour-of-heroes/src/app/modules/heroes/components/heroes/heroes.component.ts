/*
 * Author: sveera
 */
import { Component, OnInit } from '@angular/core';
import { OnDestroy } from '@angular/core';
import { Hero } from './hero';
import { HEROES } from './heroes.mock';
import { HeroService } from '../../../services/hero.service';

@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.css']
})
export class HeroesComponent implements OnInit, OnDestroy {

  private heroes: Hero[];

  constructor(private heroService: HeroService) {
    console.log(this.constructor.name + ' Instance created');
  }

  ngOnInit() {
    console.log(this.constructor.name + ' Instance initialized');
    this.getHeroes();
  }

  ngOnDestroy(): void {
    console.log(this.constructor.name + ' Instance destroyed ');
  }

  private getHeroes(): void {
    this.heroService.getHeroes().subscribe((heros) => { this.heroes = heros; });
  }

}
