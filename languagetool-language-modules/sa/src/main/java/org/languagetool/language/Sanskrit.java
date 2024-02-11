/* LanguageTool, a natural language style checker 
 * Copyright (C) 2011 Daniel Naber (http://www.danielnaber.de)
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package org.languagetool.language;

// import org.jetbrains.annotations.NotNull;
// import org.jetbrains.annotations.Nullable;
import org.languagetool.Language;
import org.languagetool.LanguageMaintainedState;
import org.languagetool.UserConfig;
// import org.languagetool.rules.sa.MorfologikSanskritSpellerRule;
import org.languagetool.rules.sa.SanskritHunspellSpellerRule;
import org.languagetool.rules.*;
import org.languagetool.rules.spelling.SpellingCheckRule;
// import org.languagetool.tagging.Tagger;
import org.languagetool.tokenizers.SRXSentenceTokenizer;
import org.languagetool.tokenizers.SentenceTokenizer;
// import org.languagetool.tokenizers.Tokenizer;
// import org.languagetool.tokenizers.sa.SanskritWordTokenizer;

import java.io.IOException;
import java.util.*;

public class Sanskrit extends Language {

  @Override
  public String getName() {
    return "Sanskrit";
  }

  @Override
  public String getShortCode() {
    return "sa";
  }

  @Override
  public String[] getCountries() {
    return new String[] { "IN" };
  }

  @Override
  public Contributor[] getMaintainers() {
    return new Contributor[] { new Contributor("Prasanna Venkatesh T S") };
  }

  @Override
  public LanguageMaintainedState getMaintainedState() {
    return LanguageMaintainedState.ActivelyMaintained;
  }

  @Override
  public List<Rule> getRelevantRules(ResourceBundle messages, UserConfig userConfig, Language motherTongue,
      List<Language> altLanguages) throws IOException {
    return Arrays.asList(
        new MultipleWhitespaceRule(messages, this),
        new DoublePunctuationRule(messages),
        new GenericUnpairedBracketsRule(messages),
        new CommaWhitespaceRule(messages, true),

        // new MorfologikSanskritSpellerRule(messages, this, userConfig, altLanguages)
        new SanskritHunspellSpellerRule(messages, userConfig));
  }

  @Override
  public SentenceTokenizer createDefaultSentenceTokenizer() {
    return new SRXSentenceTokenizer(this);
  }

  // @NotNull
  // @Override
  // public Tagger createDefaultTagger() {
  // return new Tagger();
  // }

  // @Override
  // public Tokenizer createDefaultWordTokenizer() {
  // return new SanskritWordTokenizer();
  // }

  @Override
  protected SpellingCheckRule createDefaultSpellingRule(ResourceBundle messages) throws IOException {
    // return new MorfologikSanskritSpellerRule(messages, this, null,
    // Collections.emptyList());
    return new SanskritHunspellSpellerRule(messages);
  }
}
