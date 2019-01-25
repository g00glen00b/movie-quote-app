import {interval, from} from 'rxjs';
import {delay, map, startWith, switchMap, tap} from 'rxjs/operators';
import {get} from 'axios';

interval(10000)
  .pipe(
    startWith(0),
    switchMap(() => from(randomQuote())),
    tap(hideQuote),
    delay(500),
    map(result => result.data))
  .subscribe(displayQuote);

function hideQuote() {
  document.querySelector('blockquote').classList.add('hidden');
}

function randomQuote() {
  return get(`${API_URL}/movie-quote/@random`);
}

function displayQuote({quote, source}) {
  document.querySelector('blockquote').classList.remove('hidden');
  document.querySelector('blockquote p').innerText = `“${quote}„`;
  document.querySelector('blockquote footer').innerText = source;
}
