/**
 * 
 */
package querqy.rewrite;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import querqy.model.ExpandedQuery;

/**
 * @author rene
 *
 */
public class RewriteChain {

   final List<RewriterFactory> factories;

   public RewriteChain() {
      this(Collections.<RewriterFactory> emptyList());
   }

   public RewriteChain(List<RewriterFactory> factories) {
      this.factories = factories;
   }

   public ExpandedQuery rewrite(ExpandedQuery query, Map<String, ?> context) {
      ExpandedQuery work = query;
      for (RewriterFactory factory : factories) {
         QueryRewriter rewriter = factory.createRewriter(work, context);
         work = rewriter.rewrite(work);
      }
      return work;
   }
}
